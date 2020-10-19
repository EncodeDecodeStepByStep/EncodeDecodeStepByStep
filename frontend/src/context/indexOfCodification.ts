import createGlobalState from 'react-create-global-state';

const [useIndex, IndexProvider] = createGlobalState(1)

export { useIndex, IndexProvider }
