import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HermancardsSharedModule } from 'app/shared';
import {
    CardComponent,
    CardDetailComponent,
    CardUpdateComponent,
    CardDeletePopupComponent,
    CardDeleteDialogComponent,
    cardRoute,
    cardPopupRoute
} from './';

const ENTITY_STATES = [...cardRoute, ...cardPopupRoute];

@NgModule({
    imports: [HermancardsSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CardComponent, CardDetailComponent, CardUpdateComponent, CardDeleteDialogComponent, CardDeletePopupComponent],
    entryComponents: [CardComponent, CardUpdateComponent, CardDeleteDialogComponent, CardDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HermancardsCardModule {}
