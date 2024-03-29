import { useState, useEffect } from 'react'
import { DataGrid } from '@mui/x-data-grid'
import config from './ColumnConfig'
import { useNavigate } from 'react-router-dom'

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
  const [sortModel, setSortModel] = useState()
  const [data, setData] = useState({ books: [] })
  const navigate = useNavigate()

  useEffect(() => {
    const searchParam = `search=${searchString}`
    const pageParam = `pageNumber=${paginationModel.page}&pageSize=${paginationModel.pageSize}`
    const orderParam =
      sortModel &&
      sortModel.length &&
      `sortBy=${sortModel[0].field}&order=${sortModel[0].sort}`
    let queryParams = `${searchParam}&${pageParam}`

    if (orderParam) queryParams += `&${orderParam}`

    const fetcher = () => {
      fetch(`http://localhost:8090/books?${queryParams}`)
        .then((response) => response.json())
        .then((data) => {
          setData(data)
        })
    }
    fetcher()
  }, [paginationModel, sortModel, filterModel, searchString])

  const navigateToBookDetails = (params) => {
    const bookId = params.row.id
    navigate(`/book/${bookId}`)
  }

  const isInTestingMode = process.env.JEST_WORKER_ID

  return (
    <div style={gridStyle} data-testid="list-table">
      <DataGrid
        onRowClick={(params) => navigateToBookDetails(params)}
        rows={data.books}
        disableRowSelectionOnClick
        columns={config}
        initialState={{
          pagination: {
            paginationModel,
          },
        }}
        rowCount={data.totalNoOfBooks}
        pagination
        // sortingMode="server"
        filterMode="server"
        paginationMode={isInTestingMode ? 'client' : 'server'}
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
