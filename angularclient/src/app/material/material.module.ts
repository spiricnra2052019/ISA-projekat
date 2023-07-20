import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from "@angular/material/dialog";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        MatInputModule,
        MatButtonModule,
        MatTableModule,
        MatCardModule,
        MatIconModule,
        MatTooltipModule,
        MatFormFieldModule,
        MatCardModule,
        MatDialogModule,
        MatDatepickerModule,        // <----- import(must)
        MatNativeDateModule,        // <----- import for date formating(optional)
    ],
    exports: [
        CommonModule,
        MatInputModule,
        MatButtonModule,
        MatTableModule,
        MatCardModule,
        MatIconModule,
        MatTooltipModule,
        MatFormFieldModule,
        MatCardModule,
        MatDialogModule,
        MatDatepickerModule,        // <----- import(must)
        MatNativeDateModule,        // <----- import for date formating(optional)
    ]
})
export class MaterialModule { }