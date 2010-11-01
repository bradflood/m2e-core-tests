/*******************************************************************************
 * Copyright (c) 2008 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package org.eclipse.m2e.core.internal.index;

import java.io.File;

import org.eclipse.m2e.core.embedder.ArtifactKey;
import org.eclipse.m2e.core.embedder.ILocalRepositoryListener;
import org.eclipse.m2e.core.index.IIndex;

public class IndexingTransferListener implements ILocalRepositoryListener {

  private final NexusIndexManager indexManager;

  public IndexingTransferListener(NexusIndexManager indexManager) {
    this.indexManager = indexManager;
  }

  public void artifactInstalled(File repositoryBasedir, ArtifactKey artifact, File artifactFile) {
    NexusIndex localIndex = indexManager.getLocalIndex();
    if(artifactFile.getName().endsWith(".jar")) {
      localIndex.addArtifact(artifactFile, artifact, //
          artifactFile.length(), artifactFile.lastModified(), artifactFile, //
          IIndex.NOT_PRESENT, IIndex.NOT_PRESENT);
    }
  }

}