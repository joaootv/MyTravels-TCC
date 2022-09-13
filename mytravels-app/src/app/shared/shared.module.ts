import { PopoverComponent } from './popover/popover.component';
import { HeaderComponent } from './header/header.component';
import { LOCALE_ID, ModuleWithProviders, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule
  ],
  declarations: [
    HeaderComponent,
    PopoverComponent
  ],
  exports: [
    HeaderComponent,
    PopoverComponent
  ]
})
export class SharedModule {
  static forRoot(): ModuleWithProviders<SharedModule> {
    return {
      ngModule: SharedModule,
      providers: [{ provide: LOCALE_ID, useValue: 'pt' }]
    };
  }
}
