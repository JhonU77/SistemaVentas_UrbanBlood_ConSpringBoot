function sum(a, b) {
  return a + b;
}

test("La suma funciona", () => {
  expect(sum(5, 3)).toBe(8);
});

function fallo() {}

const data = {
  nombre: "Juan",
  apellido: "Bustamante",
};
