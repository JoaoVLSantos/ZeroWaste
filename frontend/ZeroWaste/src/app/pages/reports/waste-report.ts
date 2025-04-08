export interface WasteReportQueryDTO {
  startDate: string; // formato: YYYY-MM-DD
  endDate: string;
}

export interface WasteByCategoryDTO {
  category: string;
  quantity: number;
  cost: number;
}

export interface WasteReportBodyDTO {
  totalAmount: number;
  totalCost: number;
  wastePerCategories: {
    category: string;
    quantity: number;
    cost: number;
  }[];
}

