import { render, screen } from '@testing-library/react';
import ComponentA from '../ComponentA';

test('renders header', () => {
  console.log(ComponentA)
  render(<ComponentA/>);
  const header = screen.getByText("Hi I am test component");
  expect(header).toBeInTheDocument();
});
