import { DataGrid } from '@mui/x-data-grid'
import config from './ColumnConfig'


const gridStyle = {
  height: 'calc(100vh - 60px)',
  width: '100%',
}


const  DataTable =({ books }) => {

  const navigateToBookDetails = () => {
    console.log("Row clicked--->");
  }

  return (
    <div style={gridStyle} data-testid="list-table">
      <DataGrid
        onRowClick={navigateToBookDetails}
        disableSelectionOnClick
        rows={books}
        columns={config}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        sx={{ px: 2 }}
        pageSizeOptions={[5, 10]}
      />
    </div>
  )
}


export default DataTable
