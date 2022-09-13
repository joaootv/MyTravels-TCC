import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewViagemComponent } from './view-viagem.component';

describe('ViewViagemComponent', () => {
  let component: ViewViagemComponent;
  let fixture: ComponentFixture<ViewViagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewViagemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewViagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
