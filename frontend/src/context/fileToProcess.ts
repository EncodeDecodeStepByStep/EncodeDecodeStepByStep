import createGlobalState from 'react-create-global-state';

const [useFileToProcess, FileToProcessProvider] = createGlobalState("")

export { useFileToProcess, FileToProcessProvider }
