import { Component, inject, signal, computed } from '@angular/core';
import { ReportsService } from '../../../services/ReportsService';
import { WasteReportBodyDTO } from '../waste-report';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ButtonComponent } from "../../../components/form/button/button.component";
import { InputComponent } from '../../../components/form/input/input.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { CardComponent, CardContentComponent, CardHeaderComponent } from '../../../components/card/card.component';

@Component({
  selector: 'app-waste-reports-page',
  imports: [CommonModule, ButtonComponent, InputComponent, ReactiveFormsModule, CardComponent, CardHeaderComponent, CardContentComponent, NgxChartsModule],
  templateUrl: './waste-reports-page.component.html',
  styleUrl: './waste-reports-page.component.css',
  providers: [ReportsService],
  standalone: true,

})

export class WasteReportsPageComponent {

  // Injeção dos serviços
  reportService: ReportsService = inject(ReportsService);
  private fb = inject(FormBuilder);

  // Propriedades da página
  public wasteReport = signal<WasteReportBodyDTO>({
    totalAmount: 0,
    totalCost: 0,
    wastePerCategories: [],
  });

  public datePeriodForm = this.fb.group({
    startDate: ['', [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)]],
    endDate: ['', [Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/)]],
  });

  // Funções da página
  /** Função chamada quando o usuário clica no botão de buscar relatório */
  public async onSubmit(event: SubmitEvent): Promise<void> {
    event.preventDefault();

    Object.values(this.datePeriodForm.controls).forEach(control => {
      control.markAsTouched();
    });

    if (this.datePeriodForm.invalid)
      return;

    try {
      const response = await this.reportService.getWasteReport(new Date("2025-04-07"), new Date("2025-04-10"));
      this.wasteReport.set(response);

    }
    catch (error) {
      console.error('Erro ao obter relatórios', error);
      alert('Erro ao obter relatórios');
    }
  }

  /** Função que recebe um array de informações e retorna um gráfico de barras */
  barChartData = computed(() => {
    const report = this.wasteReport();
    return report.wastePerCategories.map(item => ({
      name: item.category,
      value: item.cost
    }));
  });
}
