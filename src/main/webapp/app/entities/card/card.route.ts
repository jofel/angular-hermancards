import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Card } from 'app/shared/model/card.model';
import { CardService } from './card.service';
import { CardComponent } from './card.component';
import { CardDetailComponent } from './card-detail.component';
import { CardUpdateComponent } from './card-update.component';
import { CardDeletePopupComponent } from './card-delete-dialog.component';
import { ICard } from 'app/shared/model/card.model';

@Injectable({ providedIn: 'root' })
export class CardResolve implements Resolve<ICard> {
    constructor(private service: CardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICard> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Card>) => response.ok),
                map((card: HttpResponse<Card>) => card.body)
            );
        }
        return of(new Card());
    }
}

export const cardRoute: Routes = [
    {
        path: '',
        component: CardComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CardDetailComponent,
        resolve: {
            card: CardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CardUpdateComponent,
        resolve: {
            card: CardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CardUpdateComponent,
        resolve: {
            card: CardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cards'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cardPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CardDeletePopupComponent,
        resolve: {
            card: CardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cards'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
