import { Course } from "./course";
import { Student } from "./student";

export interface Result
{
  id: number;
  student: Student;
  course: Course;
  grade: number;
}
