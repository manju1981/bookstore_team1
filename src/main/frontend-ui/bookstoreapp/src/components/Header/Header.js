import React, { useState, useMemo } from 'react'
import { styled, alpha } from '@mui/material/styles'
import AppBar from '@mui/material/AppBar'
import Box from '@mui/material/Box'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import InputBase from '@mui/material/InputBase'
import MenuIcon from '@mui/icons-material/Menu'
import SearchIcon from '@mui/icons-material/Search'
import logo from '../../assets/book.png'
import { debounce } from 'lodash'

const Search = styled('div')(({ theme }) => ({
  position: 'relative',
  borderRadius: theme.shape.borderRadius,
  backgroundColor: alpha(theme.palette.common.white, 0.15),
  '&:hover': {
    backgroundColor: alpha(theme.palette.common.white, 0.25),
  },
  marginLeft: 0,
  width: '100%',
  [theme.breakpoints.up('sm')]: {
    marginLeft: theme.spacing(1),
    width: 'auto',
  },
}))

const SearchIconWrapper = styled('div')(({ theme }) => ({
  padding: theme.spacing(0, 2),
  height: '100%',
  position: 'absolute',
  pointerEvents: 'none',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
}))

const StyledInputBase = styled(InputBase)(({ theme }) => ({
  color: 'inherit',
  width: '100%',
  '& .MuiInputBase-input': {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)})`,
    transition: theme.transitions.create('width'),
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}))

export default function SearchAppBar({ title, onSearch }) {
  // eslint-disable-next-line no-unused-vars
  const [searchValue, setSearchValue] = useState('')

  const debouncedSearch = useMemo(
    () =>
      debounce((value) => {
        onSearch(value)
      }, 500),
    [onSearch]
  )

  const handleSearchChange = (event) => {
    const { value } = event.target
    setSearchValue(value)
    debouncedSearch(value)
  }

  return (
    <Box sx={{ flexGrow: 1 }} data-testid="header-bar">
      <AppBar position="static">
        <Toolbar>
          <MenuIcon />
          <Box
            component="img"
            sx={{
              height: 50,
              width: 50,
              ml: 2,
            }}
            alt="The house from the offer."
            src={logo}
          />

          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
            data-testid="header-bar-title"
          >
            {title}
          </Typography>
          <Search>
            <SearchIconWrapper>
              <SearchIcon />
            </SearchIconWrapper>
            <StyledInputBase
              placeholder="Search…"
              inputProps={{ 'aria-label': 'search' }}
              onChange={handleSearchChange}
            />
          </Search>
        </Toolbar>
      </AppBar>
    </Box>
  )
}
