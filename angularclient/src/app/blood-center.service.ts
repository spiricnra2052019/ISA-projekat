import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodCenter } from './blood-center';

@Injectable({
  providedIn: 'root'
})
export class BloodCenterService {
  private bloodCentersUrl: string;
  private searchUrl: string;
  private filterUrl: string;

  constructor(private http: HttpClient) {
    this.bloodCentersUrl = 'http://localhost:8080/blood-centers';
    this.searchUrl = 'http://localhost:8080/blood-centers/search?query=';
    this.filterUrl = 'http://localhost:8080/blood-centers/filter?searchQuery=';
  }

  public findAll(): Observable<BloodCenter[]> {
    return this.http.get<BloodCenter[]>(this.bloodCentersUrl);
  }

  public save(center: BloodCenter) {
    return this.http.post<BloodCenter>(this.bloodCentersUrl, center);
  }

  public findAllAndSearch(searchProperty): Observable<BloodCenter[]> {
    return this.http.get<BloodCenter[]>(this.searchUrl.concat(searchProperty));
  }

  public findAllAndFilter(searchAndFilter): Observable<BloodCenter[]> {
    return this.http.get<BloodCenter[]>(this.filterUrl.concat(searchAndFilter.searchProperty).concat('&filterQuery=')
      .concat(searchAndFilter.filterProperty));
  }

}
