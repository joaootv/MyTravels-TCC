import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewVeiculoComponent } from './view-veiculo.component';

describe('ViewVeiculoComponent', () => {
  let component: ViewVeiculoComponent;
  let fixture: ComponentFixture<ViewVeiculoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewVeiculoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewVeiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
