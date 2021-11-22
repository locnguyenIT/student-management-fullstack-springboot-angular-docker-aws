import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule , routingComponent} from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';

import { StudentService } from './service/student.service';
import { ClassroomService } from './service/classroom.service';
import { FacultyService } from './service/faculty.service';
import { CourseService } from './service/course.service';
import { ResultService } from './service/result.service';
import { LibraryCardService } from './service/library-card.service';


@NgModule({
  declarations: [
    AppComponent,
    routingComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule, //httpClient
    FormsModule, //Form
    AppRoutingModule
  ],
  providers: [StudentService,ClassroomService,FacultyService,CourseService,ResultService,LibraryCardService], //Declare Service
  bootstrap: [AppComponent]
})
export class AppModule { }
