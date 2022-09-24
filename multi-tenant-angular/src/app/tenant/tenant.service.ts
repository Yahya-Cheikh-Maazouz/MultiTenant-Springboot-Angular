import { Injectable } from '@angular/core';
import { HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TenantService {
  constructor() {}

  getTenantForHostname(hostname: string): any {
    return this.getTenantForHost(hostname.split(".")[0]);
  }

  getTenantForHost(host: string): any {
    return host;
  }

  getTenant(): any {
    return this.getTenantForHostname(location.hostname);
  }

  addTenantToHeaders(headers: HttpHeaders): HttpHeaders {
    return headers.append("X-Tenant-ID", this.getTenant());
  }
}


