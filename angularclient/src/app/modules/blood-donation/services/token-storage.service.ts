import { Injectable } from '@angular/core';
import { UserToken } from '../model/user-token';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.removeItem(USER_KEY);
  }
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    console.log(token)
    console.log("saveToken")
  }
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
  public isLoggedIn(): boolean {
    return !!window.sessionStorage.getItem(TOKEN_KEY);
  }
  public saveUser(token: string): void {
    let user: string = atob(token.split('.')[1]);
    let userObject = JSON.parse(user)
    let userTk: UserToken = new UserToken(userObject.sub, userObject.id, userObject.role);
    console.log(userTk);
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(userTk));

  }
  public getUser(): UserToken {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
    return new UserToken("", "", 0);
  }
}