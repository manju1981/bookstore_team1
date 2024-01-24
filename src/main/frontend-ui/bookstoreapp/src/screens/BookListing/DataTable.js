import { useState, useEffect } from 'react'
import { DataGrid } from '@mui/x-data-grid'
import config from './ColumnConfig'

const gridStyle = {
  height: 'calc(100vh - 60px)',
  width: '100%',
}
const DataTable = ({ searchString }) => {
  const [paginationModel, setPaginationModel] = useState({
    page: 0,
    pageSize: 5,
  })

  const [filterModel, setFilterModel] = useState({ items: [] })
  const [sortModel, setSortModel] = useState([])
  const [rows, setRows] = useState({ data: [] })

  useEffect(() => {
    const fetcher = () => {
      fetch(
        `http://localhost:8090/books?search=${searchString}&pageNumber=${paginationModel.page}&pageSize=${paginationModel.pageSize}`
      )
        .then((response) => response.json())
        .then((data) => {
          setRows({ data: data, rowCount: 14 })
        })
    }
    fetcher()
  }, [paginationModel, sortModel, filterModel, setRows, searchString])

  const navigateToBookDetails = () => {
    console.log('Row clicked--->')
  }

  return (
    <div style={gridStyle} data-testid="list-table">
      <DataGrid
        onRowClick={navigateToBookDetails}
        rows={rows.data}
        disableRowSelectionOnClick
        columns={config}
        initialState={{
          pagination: {
            paginationModel,
          },
        }}
        rowCount={14}
        pagination
        // sortingMode="server"
        filterMode="server"
        paginationMode="server"
        onPaginationModelChange={setPaginationModel}
        onSortModelChange={setSortModel}
        onFilterModelChange={setFilterModel}
        sx={{ px: 2 }}
        pageSizeOptions={[5, 10]}
      />
    </div>
  )
}

export default DataTable
