import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { QuotesComponent } from './quotes/quotes.component';


const routes: Routes = [
  { path: 'home', component: AppComponent },
  { path: 'quotes', component: QuotesComponent },
  { path: 'new', component: QuotesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
