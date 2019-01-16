import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from "../model/user.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../model/api.response";
import { Article } from '../model/article.model';

@Injectable()
export class ApiService {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'http://localhost:8080/users/';
  articleApiUrl: string = 'http://localhost:8080/api/articles/'

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>('http://localhost:8080/' + 'token/generate-token', loginPayload);
  }

  getUsers() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }

  getUserById(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl + id);
  }

  createUser(user: User): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl, user);
  }

  updateUser(user: User): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl + user.id, user);
  }

  deleteUser(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }

  getArticles() : Observable<ApiResponse> {
    return this.http.get<ApiResponse> (this.articleApiUrl);
  }

  createArticle(article: Article, video: File): Observable<ApiResponse> {
    const formData:FormData = new FormData();
    formData.append('video', video);
    formData.append('article', JSON.stringify(article));
    return this.http.post<ApiResponse>(this.articleApiUrl, formData);
  }

  deleteArticle(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.articleApiUrl + id);
  }

  getArticle(id: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse> (this.articleApiUrl + id);
  }
}
