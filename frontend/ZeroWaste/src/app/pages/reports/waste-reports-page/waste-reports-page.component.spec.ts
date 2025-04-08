import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WasteReportsPageComponent } from './waste-reports-page.component';

describe('WasteReportsPageComponent', () => {
  let component: WasteReportsPageComponent;
  let fixture: ComponentFixture<WasteReportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WasteReportsPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WasteReportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
