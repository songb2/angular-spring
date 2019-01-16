import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {Router} from "@angular/router";
import {ApiService} from "../core/api.service";

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private apiService: ApiService) { }

  addForm: FormGroup;
  video: File;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      categoryId: [0, Validators.required],
      name: ['', Validators.required],
      title: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  setVideo(files: FileList) {
    this.video = files.item(0);
  }
  onSubmit() {
    this.apiService.createArticle(this.addForm.value, this.video)
      .subscribe( data => {
        this.router.navigate(['list-article']);
      });
  }
}
