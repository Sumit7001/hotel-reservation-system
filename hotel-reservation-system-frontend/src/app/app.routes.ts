import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { RoomsComponent } from './components/rooms/rooms.component';
import { ViewReservationComponent } from './components/view-reservation/view-reservation.component';
import { UpdateReservationComponent } from './components/update-reservation/update-reservation.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
    {path:'',component:HomeComponent},
    {path:"login",component:LoginComponent},
    {path:"register",component:RegisterComponent},
    {path:"reservation/:id",component:ReservationComponent},
    {path:"room-details",component:RoomsComponent},
    {path:"view-reservation",component:ViewReservationComponent},
    {path:"update-reservation/:id",component:UpdateReservationComponent}
    //{path:"update-delete",component:UpdateCancleeComponent}
];
