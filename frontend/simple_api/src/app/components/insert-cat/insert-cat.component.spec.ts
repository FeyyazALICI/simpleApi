import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertCatComponent } from './insert-cat.component';

describe('InsertCatComponent', () => {
  let component: InsertCatComponent;
  let fixture: ComponentFixture<InsertCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InsertCatComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsertCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
