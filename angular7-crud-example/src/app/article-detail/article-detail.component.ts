import { Component, OnInit } from '@angular/core';
import { Article } from '../model/article.model';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../core/api.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {

  article:Article;
  private base64Video: any;
  constructor(private activeRoute: ActivatedRoute, private apiService: ApiService) { }

  ngOnInit() {
    const id: string = this.activeRoute.snapshot.params.id;
    this.apiService.getArticle(+id)
      .subscribe( data => {
        this.article = data.result;
        this.base64Video = "data:video/mp4;base64," + data.result.video;
      });
  }

}
