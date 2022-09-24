import { Component, OnInit, HostBinding } from "@angular/core";
import {  TenantService } from './tenant/tenant.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css', './app.component.skins.less']
})
export class AppComponent implements OnInit {

  title = 'multi-tenant-angular';

  constructor(private tenantService: TenantService){
  }

  ngOnInit() {
  }

  get tenant() : string {
    return this.tenantService.getTenant();
  }


}
