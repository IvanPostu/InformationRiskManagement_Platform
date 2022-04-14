import styled from 'styled-components'

export const FixedMultilineSpan = styled.span<{ linesCount: number }>`
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: ${(props) => props.linesCount};
`
