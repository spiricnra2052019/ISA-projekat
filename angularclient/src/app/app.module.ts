import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { NavbarComponent } from "./modules/navbar/navbar.component";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";
import { MaterialModule } from "./material/material.module";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { PagesModule } from "./modules/pages/pages.module";


@NgModule(
  {
    declarations: [
      AppComponent,
      NavbarComponent,
    ],
    imports: [
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      HttpClientModule,
      MaterialModule,
      PagesModule,
      FormsModule,
      CommonModule
    ],
    providers: [],
    bootstrap: [AppComponent]
  }
)
export class AppModule { }