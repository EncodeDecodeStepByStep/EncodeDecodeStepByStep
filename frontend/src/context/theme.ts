import createGlobalState from 'react-create-global-state';

const [useTheme, ThemeProvider] = createGlobalState(false)

export { useTheme, ThemeProvider }
