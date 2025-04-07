import { Routes } from "@angular/router";
import { authGuard } from "../../auth/auth.guard";
import { ListBroadcastPageComponent } from "./list-broadcast-page/list-broadcast-page.component";
import { CreateBroadcastListFormPageComponent } from "./create-broadcast-list-form-page/create-broadcast-list-form-page.component";
import { UpdateBroadcastListFormPageComponent } from "./update-broadcast-list-form-page/update-broadcast-list-form-page.component";
import { DetailBroadcastListPageComponent } from "./detail-broadcast-list-page/detail-broadcast-list-page.component";

export const routes: Routes = [
  {
    path: 'broadcasts',
    children: [
      {
        path: '',
        title: 'Listas de transmissão',
        component: ListBroadcastPageComponent,
        canActivate: [authGuard],
      },
      {
        path: ':id',
        title: 'Detalhes da lista de transmissão',
        component: DetailBroadcastListPageComponent,
        canActivate: [authGuard],
        data: { role: 'ADMIN' },
      },
      {
        path: 'create',
        title: 'Cadastrar lista de transmissão',
        component: CreateBroadcastListFormPageComponent,
        canActivate: [authGuard],
        data: { role: 'ADMIN' },
      },
      {
        path: 'update/:id',
        title: 'Atualizar lista de transmissão',
        component: UpdateBroadcastListFormPageComponent,
        canActivate: [authGuard],
        data: { role: 'ADMIN' },
      },
    ]
  }
]
