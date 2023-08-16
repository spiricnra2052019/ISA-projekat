import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodCenter } from '../model/blood-center';

@Injectable({
  providedIn: 'root'
})
export class BloodCenterService {
  private bloodCentersUrl: string;
  private searchUrl: string;
  private filterUrl: string;
  private sortUrl: string;

  constructor(private http: HttpClient) {
    this.bloodCentersUrl = 'http://localhost:8080/blood-centers';
    this.searchUrl = 'http://localhost:8080/blood-centers/search?query=';
    this.filterUrl = 'http://localhost:8080/blood-centers/filter?searchQuery=';
    this.sortUrl = 'http://localhost:8080/blood-centers/sort?sortBy=';
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
  public findById(id) {
    return this.http.get<BloodCenter>(this.bloodCentersUrl + "/" + id);
  }
  public edit(bloodCenter) {
    return this.http.put<BloodCenter>(this.bloodCentersUrl, bloodCenter);
  }
  public sortBy(sortBy) {
    return this.http.get<BloodCenter[]>(this.sortUrl.concat(sortBy));
  }

  public findBloodCenterIdByAdministratorId(administratorId) {
    return this.http.get<BloodCenter>(this.bloodCentersUrl + "/administrator/" + administratorId);
  }
}
