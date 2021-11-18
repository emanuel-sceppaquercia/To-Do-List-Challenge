import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TaskDto } from '../interface/taskDto';
import { TaskRequestDto } from '../interface/taskRequestDto';

@Injectable({providedIn: 'root'})
export class TaskService{
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    public createTask(task: TaskRequestDto): Observable<TaskDto>{
        return this.http.post<TaskDto>(`${this.apiServerUrl}/task`, task);
    }

    public editTask(task: TaskDto): Observable<TaskDto>{
        return this.http.put<TaskDto>(`${this.apiServerUrl}/task/${task.id}`, task);
    }

    public deleteTask(taskId: number): Observable<void>{
        return this.http.delete<void>(`${this.apiServerUrl}/task/${taskId}`);
    }

    public checkTask(taskId: number): Observable<void>{
        return this.http.put<void>(`${this.apiServerUrl}/task/check/${taskId}`, null);
    }

}