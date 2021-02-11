### Teste de Admissão Trílogo.

## Joel Araújo da Silva

### Funções disponíveis no App:

> Visualizar a informação de mais de 100 filmes em cartas <br />

### Bibliotecas utilizadas:

> implementation 'com.squareup.retrofit2:converter-gson:2.5.0' (Utilização necessária para requests com o Retrofit) <br />
> implementation 'com.squareup.retrofit2:retrofit:2.5.0' (Biblioteca Retrofit) <br />
> implementation 'androidx.recyclerview:recyclerview:1.1.0' (RecyclerView para exibição das listas) <br />
> implementation 'androidx.navigation:navigation-fragment-ktx:2.3.3'(Biblioteca necessária para navegação entre Fragments) <br />
> implementation 'androidx.navigation:navigation-ui-ktx:2.3.3' (Biblioteca de navegação do Android X) <br />
> kapt 'com.android.databinding:compiler:3.1.4' (Kapt para utilização do DataBinding) <br />
> implementation 'com.github.bumptech.glide:glide:4.12.0' (Biblioteca Glide para carregamento de imagens em Views) <br />
> annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0' (Compilador necessário para o funcionamento do Glide) <br />


### Informações gerais:

> Versão do Kotlin utilizada : 1.3.72 <br />
> Versão do Android Studio : 4.1.2.0 <br />
> Dispositivo utilizado como referência : Google Nexus 5 (768x1280, 320dpi) <br />

### Informações Adicionais:

> Percebendo que as requests para a API do MoviDb retornam páginas de filmes resolvi salvar as requests já feitas pelo usuário em um MutableMap<Int (número da página), List<Movie> (Filmes daquela página)> para que não fosse necessário realizar a mesma request toda vida que o usuário selecionasse uma nova página para visualizar.
> Optei pela utilização da biblioteca Glide tendo em vista que a lista de filmes ultrapassa facilmente a casa dos 150 itens, e por isso as memórias carregadas e salvas em cache ajudam bastante na exibição rápida das views.


