import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {ListUserComponent} from "./list-user/list-user.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import { ListArticleComponent } from './list-article/list-article.component';
import { AddArticleComponent } from './add-article/add-article.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: 'list-article', component: ListArticleComponent},
  { path: 'add-article', component: AddArticleComponent},
  { path: 'article-detail/:id', component: ArticleDetailComponent},
  {path : '', component : LoginComponent}
];

export const routing = RouterModule.forRoot(routes);
