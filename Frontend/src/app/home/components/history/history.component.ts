import { Component, OnInit } from '@angular/core';
import { HistoryService } from './history.service';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-history',
  imports: [MatTableModule, MatSelectModule, CommonModule, MatFormFieldModule],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>([]);
  displayedColumns: string[] = ['name', 'type', 'date'];
  movimientos: any[] = [
    {value: '', viewValue: 'Todos'},
    {value: 'Entrada', viewValue: 'Entradas'},
    {value: 'Salida', viewValue: 'Salidas'},
  ];
  constructor(private historyService: HistoryService) { }

  ngOnInit(): void {
    this.getHistory();
  }

  getHistory() {
    this.historyService.getHistory().subscribe(
      (data: any) => {
        this.dataSource = new MatTableDataSource<any>(data);
      },
      (error: any) => {
        console.error('Error fetching history:', error);
      }
    );
  }

  onMovimientoFilterChange(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.dataSource.filterPredicate = (data: any, filter: string): boolean => {
      if (!filter) return true;
      return data.type.toLowerCase() === filter.toLowerCase();
    };
  }

}
