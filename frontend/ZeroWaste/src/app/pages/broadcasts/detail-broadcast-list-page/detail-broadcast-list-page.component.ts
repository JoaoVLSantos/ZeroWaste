import { Component, OnInit, inject, signal } from "@angular/core";
import { FormArray, FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { ValidationErrorMessage } from "../../../services/ValidationErrorMessage";
import { InputComponent } from "../../../components/form/input/input.component";
import { TextareaComponent } from "../../../components/form/textarea/textarea.component";
import { SelectComponent } from "../../../components/form/select/select.component";
import { ButtonComponent } from "../../../components/form/button/button.component";
import { CommonModule } from "@angular/common";
import { BroadcastService } from "../../../services/BroadcastService";
import { Product } from "../../products/product";
import { UpdateBroadcastDTO } from "../broadcast";
import { ProductService } from "../../../services/ProductService";

@Component({
  selector: 'app-detail-broadcast-list-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    InputComponent,
    TextareaComponent,
    SelectComponent,
  ],
  templateUrl: './detail-broadcast-list-page.component.html',
  styleUrl: './detail-broadcast-list-page.component.css',
})
export class DetailBroadcastListPageComponent implements OnInit {
  private fb = inject(FormBuilder);
  private validationErrorMessage = inject(ValidationErrorMessage);
  private router = inject(Router);
  public route = inject(ActivatedRoute);
  private productsService = inject(ProductService);
  private broadcastsService = inject(BroadcastService);

  public broadcastListForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
    description: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(255)]],
    sendType: ['', [Validators.required]],
    emails: this.fb.array([]),
    productsIds: [[] as string[], [Validators.required, Validators.minLength(1)]],
  });
  public products = signal<Product[]>([]);

  public get emails() {
    return this.broadcastListForm.get('emails') as FormArray;
  }

  public set emails(value: FormArray) {
    this.broadcastListForm.setControl('emails', value);
  }

  public addEmail(value = '') {
    this.emails.push(
      this.fb.control(value, [Validators.required, Validators.email])
    );
  }

  public async ngOnInit(): Promise<void> {
    this.broadcastListForm.disable();

    const products = await this.productsService.getAllProducts();
    this.products.set(products);

    const broadcastListId = this.route.snapshot.paramMap.get('id')!;

    const broadcastListData = await this.broadcastsService.getBroadcastById(Number(broadcastListId));

    broadcastListData.emails.forEach(email => {
      this.addEmail(email);
    });

    (this.broadcastListForm.get('emails') as FormArray).controls.forEach(control => {
      control.disable();
    });

    this.broadcastListForm.setValue({
      name: broadcastListData.name,
      description: broadcastListData.description,
      sendType: broadcastListData.sendType,
      emails: broadcastListData.emails,
      productsIds: broadcastListData.productsIds.map((id) => id.toString()),
    });
  }
}
