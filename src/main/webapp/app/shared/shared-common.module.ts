import { NgModule } from '@angular/core';

import { HermancardsSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [HermancardsSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [HermancardsSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class HermancardsSharedCommonModule {}
