import { API_URL } from '../utils/constants';
import { WasteReportBodyDTO } from '../pages/reports/waste-report';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  /** Obtém os dados de relatório */
  public async getWasteReport(startDate: Date, endDate: Date): Promise<WasteReportBodyDTO> {

    let a = startDate.toISOString().split('T')[0];
    let b = endDate.toISOString().split('T')[0];

    const url = `${API_URL}/products/reports/waste?startDate=${a}&endDate=${b}`;

    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
      }
    });

    if (!response.ok) {
      const errorBody = await response.text(); // ou .json(), dependendo do backend
      throw new Error(`Erro ao buscar relatório: ${response.status} - ${errorBody}`);
    }

    const { waste_report } = await response.json();

    return waste_report ?? {};
  }
}
