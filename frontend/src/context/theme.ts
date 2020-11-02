import createGlobalState from 'react-create-global-state';

const [useTheme, ThemeProvider] = createGlobalState(true)

export { useTheme, ThemeProvider }
