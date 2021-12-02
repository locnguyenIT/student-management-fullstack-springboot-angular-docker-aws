import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClassroomComponent } from './component/classroom/classroom.component';
import { CourseComponent } from './component/course/course.component';
import { FacultyComponent } from './component/faculty/faculty.component';
import { IndexComponent } from './component/index/index.component';
import { LibraryCardComponent } from './component/library-card/library-card.component';
import { MenuComponent } from './component/menu/menu.component';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { ResultComponent } from './component/result/result.component';
import { StudentComponent } from './component/student/student.component';

const routes: Routes = [
  {path: '',   redirectTo: '/index', pathMatch: 'full' }, // router level 1: /''
  {path: 'index',component: IndexComponent}, // router level 1: /index
  {path: 'menu',component: MenuComponent, // router level 1: /menu
      children:[
      {path: '',   redirectTo: 'student', pathMatch: 'full'}, // router level 2 : menu/[path]
      {path: 'student',component: StudentComponent},
      {path: 'classroom',component: ClassroomComponent},
      {path: 'faculty',component: FacultyComponent},
      {path: 'course',component: CourseComponent},
      {path: 'result',component: ResultComponent},
      {path: 'library-card',component: LibraryCardComponent},
      ]
  },
  {path: '**',component: PagenotfoundComponent} // router leve 1: Page Not Found

];

@NgModule({
//   imports: [RouterModule.forRoot(routes)],
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent = [IndexComponent,StudentComponent,ClassroomComponent,FacultyComponent,
                                CourseComponent,ResultComponent,LibraryCardComponent,MenuComponent, PagenotfoundComponent]
