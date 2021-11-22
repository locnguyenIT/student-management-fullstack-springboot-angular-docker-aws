import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LibraryCard } from 'src/app/interface/library-card';
import { Student } from 'src/app/interface/student';
import { LibraryCardService } from 'src/app/service/library-card.service';
import { StudentService } from 'src/app/service/student.service';

@Component({
  selector: 'app-library-card',
  templateUrl: './library-card.component.html',
  styleUrls: ['./library-card.component.css']
})
export class LibraryCardComponent implements OnInit {

  public listLibraryCard: LibraryCard[];
  public listStudent: Student[];
  public libraryCard: LibraryCard;

  constructor(private libraryCardService: LibraryCardService,
              private studentService: StudentService) { }

  ngOnInit(): void {
    this.getAllLibraryCard();
    this.getAllStudent();
  }

  public getLibraryCard(libraryCard: LibraryCard)
  {
    this.libraryCard = libraryCard;
  }
  public getAllLibraryCard():void
  {
    this.libraryCardService.getAllLibraryCard().subscribe(
      (response: LibraryCard[]) => {
        this.listLibraryCard = response;
        console.log(this.listLibraryCard)
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    )
  }

  public getAllStudent():void
  {
      this.studentService.getAllStudent().subscribe(
        (response: Student[]) => {
          this.listStudent = response;
          console.log(this.listStudent);
        },
        (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      );
  }

  public addLibraryCard(addForm: NgForm): void
  {
      document.getElementById('add-library-card-close').click();
      this.libraryCardService.addLibraryCard(addForm.value,addForm.value.studentId).subscribe(
        (response: LibraryCard) => {
          console.log(response);
          alert('Added library card successfully')
          this.getAllLibraryCard();
          addForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      );
  }

  public deleteLibraryCard(libraryCardId: number):void
  {
    document.getElementById('btn-no-delete').click();
    this.libraryCardService.deleteLibraryCard(libraryCardId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAllLibraryCard();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public updateLibraryCard(updateForm: NgForm):void
  {
    document.getElementById('btn-edit-close').click();
    this.libraryCardService.updateLibraryCard(updateForm.value.id,updateForm.value.card_number).subscribe(
      (response: LibraryCard) => {
        console.log(response);
        alert('Update successfully');
        this.getAllLibraryCard();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public searchLiraryCard(input: string):void
  {
    console.log(input);
    const result: LibraryCard[] = [];
    console.log(result);
    for(const card of this.listLibraryCard) //loop of js
    {
      if(card?.student?.name.toLowerCase().indexOf(input.toLowerCase()) !== -1 //if scard?.student?.name include input
        || card?.card_number.indexOf(input) !== -1) //if card?.card_number include input
      {
        result.push(card); //push LibraryCard in result array
      }

    }
    this.listLibraryCard = result; //filter new list library card when input match scard?.student?.name or card?.card_number
    if(result.length === 0 || !input) //if result empty or input empty
    {
      this.getAllLibraryCard(); //reload listLibraryCar
    }
  }

}
