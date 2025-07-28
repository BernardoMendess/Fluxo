import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component'
import { TransacaoListComponent } from './pages/transacao/transacao-list/transacao-list.component';
import { TransacaoFormComponent } from './pages/transacao/transacao-form/transacao-form.component';
import { CategoriaListComponent } from './pages/categoria/categoria-list/categoria-list.component';
import { CategoriaFormComponent } from './pages/categoria/categoria-form/categoria-form.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AuthGuard } from './services/auth-guard.service';

export const routes: Routes = [
    {
        path:"login",
        component: LoginComponent
    },
    {
        path:"signup",
        component: SignupComponent
    },
    {
        path:"transacao",
        component: TransacaoListComponent,
        canActivate: [AuthGuard]
    },
    {
        path:"transacao/novo",
        component: TransacaoFormComponent,
        canActivate: [AuthGuard]
    },
    {
        path:"categoria",
        component: CategoriaListComponent,
        canActivate: [AuthGuard]
    },
    {
        path:"categoria/novo",
        component: CategoriaFormComponent,
        canActivate: [AuthGuard]
    }
];
