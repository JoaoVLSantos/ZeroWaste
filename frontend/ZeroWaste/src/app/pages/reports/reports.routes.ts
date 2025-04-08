import { Routes } from "@angular/router";
import { authGuard } from "../../auth/auth.guard";
import { WasteReportsPageComponent } from "./waste-reports-page/waste-reports-page.component";

export const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'reports/waste',
        title: 'Waste Reports',
        component: WasteReportsPageComponent,
        canActivate: [authGuard],
        data: { role: 'ADMIN' },
      },
    ],
  }
];
