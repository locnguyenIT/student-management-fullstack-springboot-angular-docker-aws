import { Gender } from "../enum/gender.enum";
import { Classroom } from "./classroom";

export interface Student
{
  id: number;
  name: string;
  email: string;
  gender: Gender;
  address: string;
  dob: Date;
  classroom: Classroom;
}
