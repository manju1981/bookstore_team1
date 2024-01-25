import { render, fireEvent, act, waitFor } from '@testing-library/react'
import SearchAppBar from '../Header/Header'
import { MemoryRouter } from 'react-router-dom'

beforeEach(() => {
  jest.useFakeTimers()
})

test('renders SearchAppBar component', () => {
  render(
    <MemoryRouter>
      <SearchAppBar title="Test Title" onSearch={() => {}} />
    </MemoryRouter>
  )
})

test('displays the correct title', () => {
  const { getByTestId } = render(
    <MemoryRouter>
      <SearchAppBar title="Test Title" onSearch={() => {}} />
    </MemoryRouter>
  )
  const titleElement = getByTestId('header-bar-title')
  expect(titleElement.textContent).toBe('Test Title')
})

test('calls onSearch function when the user types into the search input', () => {
  const mockOnSearch = jest.fn()
  const { getByPlaceholderText } = render(
    <MemoryRouter>
      <SearchAppBar title="Test Title" onSearch={mockOnSearch} />
    </MemoryRouter>
  )
  const searchInput = getByPlaceholderText('Search…')

  act(() => {
    fireEvent.change(searchInput, { target: { value: 'test query' } })
    jest.runAllTimers()
  })

  expect(mockOnSearch).toHaveBeenCalledWith('test query')
})

test('debounces the search input', async () => {
  const mockOnSearch = jest.fn()
  const { getByPlaceholderText } = render(
    <MemoryRouter>
      <SearchAppBar title="Test Title" onSearch={mockOnSearch} />
    </MemoryRouter>
  )
  const searchInput = getByPlaceholderText('Search…')

  fireEvent.change(searchInput, { target: { value: 'test query' } })

  act(() => {
    jest.runAllTimers()
  })

  await waitFor(() => {
    expect(mockOnSearch).toHaveBeenCalledWith('test query')
  })
})
