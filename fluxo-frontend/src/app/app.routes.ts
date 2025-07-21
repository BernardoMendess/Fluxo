import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component'
import { TransacaoListComponent } from './pages/list-transacao/list-transacao.component';
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
    }
];
