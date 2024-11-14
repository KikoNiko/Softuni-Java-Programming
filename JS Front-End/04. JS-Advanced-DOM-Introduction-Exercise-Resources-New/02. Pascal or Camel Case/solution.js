function solve() {
  let text = document.getElementById('text');
  let namingConvention = document.getElementById('naming-convention');
  let result = document.getElementById('result');

  if (!namingConvention.value.startsWith('Pascal') && !namingConvention.value.startsWith('Camel')) {
    result.textContent = 'Error!';
    return;
  }

  const pascalText = text.value
    .toLowerCase()
    .split(" ")
    .map((x) => x[0].toUpperCase().concat(x.slice(1)))
    .join("");

  result.textContent = namingConvention.value.startsWith('Camel') ? 
  pascalText[0].toLowerCase().concat(pascalText.slice(1)) :
  pascalText;

}