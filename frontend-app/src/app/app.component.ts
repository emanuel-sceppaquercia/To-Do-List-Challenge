import { HttpErrorResponse } from '@angular/common/http';
import { ResourceLoader } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Folder } from './interface/folder';
import { FolderDto } from './interface/FolderDto';
import { RequestDto } from './interface/RequestDto';
import { TaskDto } from './interface/taskDto';
import { TaskRequestDto } from './interface/taskRequestDto';
import { FolderService } from './service/folder.service';
import { TaskService } from './service/task.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'To-Do list App';

  public currentState: String;
  public currentFolder: FolderDto;
  public taskState: boolean = false;
  public auxTask: TaskDto;

  public folders: FolderDto[];
  public tasks: TaskDto[];

  constructor(private taskService: TaskService, 
    private folderService: FolderService){}

  ngOnInit(): void{
    this.getAllFolders();
    this.currentState = "Folders";
  }

  public getAllFolders(): void {
    this.folderService.getAllFolders().subscribe(
      (response: FolderDto[]) => {
        this.folders = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteFolder(id: number): void{
    this.folderService.deleteFolder(id).subscribe(
      (response: void) => {
        console.log(response)
      },
      (error: HttpErrorResponse) => {
        //alert(error.message)
        this.getAllFolders();
      }
    );
  }
 
  public createFolder(addForm: NgForm): void{
    this.folderService.createFolder(addForm.value).subscribe(
      (response: FolderDto) => {
        console.log(response);
        this.getAllFolders();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    addForm.reset();
  }

  public viewItems(folder: FolderDto): void{
    this.currentState = `Folder/${folder.name}`;
    this.currentFolder = folder;
    this.taskState = true;
    console.log(this.currentState);

    this.folderService.getAllTasks(folder.id).subscribe(
      (response: TaskDto[]) => {
        console.log(response)
        this.tasks = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public createTask(addForm: NgForm): void{
    let taskRequest = {} as TaskRequestDto;
    taskRequest.name = addForm.value.name;
    taskRequest.folderId = this.currentFolder.id;

    this.taskService.createTask(taskRequest).subscribe(
      (response: TaskDto) => {
        this.viewItems(this.currentFolder);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    addForm.reset();
  }

  public deleteTask(id: number): void{
    this.taskService.deleteTask(id).subscribe(
      (response: void) => {
        console.log(response)
      },
      (error: HttpErrorResponse) => {
        //alert(error.message)
        this.viewItems(this.currentFolder);
      }
    );
  }

  public editTask(editForm: NgForm): void{
    let task = {} as TaskDto;
    task.id = this.auxTask.id
    task.name = editForm.value.name;
    task.finished = this.auxTask.finished;

    this.taskService.editTask(task).subscribe(
      (response: TaskDto) => {
        console.log(response)
        this.viewItems(this.currentFolder);
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
    document.getElementById("close-btn")?.click();
  }

  public setAuxTask(task: TaskDto): void{
    this.auxTask = task;
  }

  public resetInput(editForm: NgForm): void{
    document.getElementById("input")?.setAttribute
  }

  public goBack(): void{
    this.currentState = "Folders";
    this.taskState = false;
  }

 
}
