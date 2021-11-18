import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { FolderDto } from "../interface/FolderDto";
import { RequestDto } from "../interface/RequestDto";
import { TaskDto } from "../interface/taskDto";

@Injectable({providedIn: 'root'})
export class FolderService{
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    public getAllFolders(): Observable<FolderDto[]> {
        return this.http.get<FolderDto[]>(`${this.apiServerUrl}/folder`);
    } 

    public createFolder(request: RequestDto): Observable<FolderDto> {
        return this.http.post<FolderDto>(`${this.apiServerUrl}/folder`, request);
    } 

    public deleteFolder(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/folder/${id}`);
    } 

    public getAllTasks(id: number): Observable<TaskDto[]> {
        return this.http.get<TaskDto[]>(`${this.apiServerUrl}/folder/${id}`);
    }

}