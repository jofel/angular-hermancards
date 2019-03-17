import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'student',
                loadChildren: './student/student.module#HermancardsStudentModule'
            },
            {
                path: 'room',
                loadChildren: './room/room.module#HermancardsRoomModule'
            },
            {
                path: 'card',
                loadChildren: './card/card.module#HermancardsCardModule'
            },
            {
                path: 'card',
                loadChildren: './card/card.module#HermancardsCardModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HermancardsEntityModule {}
