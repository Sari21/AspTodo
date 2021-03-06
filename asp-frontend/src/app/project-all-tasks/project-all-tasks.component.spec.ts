import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectAllTasksComponent } from './project-all-tasks.component';

describe('ProjectAllTasksComponent', () => {
  let component: ProjectAllTasksComponent;
  let fixture: ComponentFixture<ProjectAllTasksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProjectAllTasksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjectAllTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
