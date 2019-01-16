import { Component, OnInit } from '@angular/core';
import { ApiService } from '../core/api.service';
import { Article } from '../model/article.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-article',
  templateUrl: './list-article.component.html',
  styleUrls: ['./list-article.component.css']
})
export class ListArticleComponent implements OnInit {

  articles: Article[];
  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    if(!window.localStorage.getItem('token')) {
      this.router.navigate(['login']);
      return;
    };
    this.apiService.getArticles().subscribe( data => {
      this.articles = data.result;
    });
  }

  addArticle(): void {
    this.router.navigate(['add-article']);
  }

  deleteArticle(article: Article) {
    this.apiService.deleteArticle(article.id).subscribe(data => {
      this.articles = this.articles.filter(a => a != article);
    });
  }

}
