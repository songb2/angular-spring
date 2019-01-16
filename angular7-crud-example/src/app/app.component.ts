import { Component } from '@angular/core';

class Menu {
  constructor(navItems: NavItem[]){
    this.navItems = navItems;
  }
  navItems:NavItem[];
}
class NavItem {
  linkUrl:string;
  linkText:string;
  isActive:boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Learn English Everyday';
  menu:Menu;
  navItems : NavItem[] = [];

  ngOnInit() {
    let articleNavItem : NavItem = {linkUrl:"/list-article", linkText:"Articles", isActive: false};
    let userNavItem : NavItem = {linkUrl:"/list-user", linkText:"Users", isActive: false};
    this.navItems.push(articleNavItem);
    this.navItems.push(userNavItem);
    this.menu = new Menu(this.navItems);
  }

  toggleState(navItem: NavItem){
    this.menu.navItems.forEach(item => {
      item.isActive = false;
    })
    navItem.isActive = true;
  }
}